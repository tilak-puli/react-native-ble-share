import Foundation

@objc(Openid4vpBle)
class Openid4vpBle: RCTEventEmitter {
    
    var callbk: RCTResponseSenderBlock?
    var viewModel: WalletViewModel = WalletViewModel()

    override init() {
        super.init()
        EventEmitter.sharedInstance.registerEventEmitter(eventEmitter: self)
    }
    
    @objc
    func noop() -> Void {}
    
    @objc
    func getConnectionParameters() -> String {
        return "GetConnectionParameters"
    }
    
    @objc(setConnectionParameters:)
    func setConnectionParameters(params: String) -> Any {
        print("SetConnectionParameters->Params::\(params)")
        var paramsObj = stringToJson(jsonText: params)
        var firstPartOfPk = paramsObj["pk"]
        print("synchronized setConnectionParameters called with", params, "and", firstPartOfPk)
        viewModel.setAdvIdentifier(advIdentifier: firstPartOfPk as! String)
        return "data" as Any
    }
    
    func stringToJson(jsonText: String) -> NSDictionary {
        var dictonary: NSDictionary?
        if let data = jsonText.data(using: String.Encoding.utf8) {
            do {
                dictonary = try JSONSerialization.jsonObject(with: data, options: []) as? [String:AnyObject] as NSDictionary?
            } catch let error as NSError {
                print(error)
            }
        }
        return dictonary!
    }
    
    @objc
    func getConnectionParametersDebug() -> String {
        return "GetConnectionParametersDebug"
    }
    
    @objc
    func destroyConnection() -> Any {
        return "check" as! Any
    }
    
    @objc
    func send(_ message: String, withCallback callback: RCTResponseSenderBlock) {
        let newMessage = String.init(format: "%::%s", message, "iOS")
        let recivedInfoSplits = message.components(separatedBy: "\n")
        if recivedInfoSplits[0] == "exchange-sender-info" {
            if #available(iOS 13.0, *) {
                viewModel.writeIdentity()
            } else {
                // Fallback on earlier versions
            }
        }
        print("new message is :::: ", message)
        callback([newMessage])
    }
    
    @objc(createConnection:withCallback:)
    func createConnection(_ mode: String, withCallback callback: @escaping RCTResponseSenderBlock) {
        let message = String.init(format: "MODE->%s", mode)
        callbk = callback
         NotificationCenter.default.addObserver(self, selector: #selector(handleCallback), name: Notification.Name(rawValue: "CONNECTED"), object: nil)
        switch mode {
        case "advertiser":
            print("advert")
        case "discoverer":
            print("disc")
            Wallet.shared.central = Central()
            if #available(iOS 13.0, *) {
               
            } else {
                print("target is low")
            }
        default:
            break
        }
    }

    @objc
    func handleCallback(notification: Notification) {
        print("call back called")
        callbk!(["data"])
    }

    @objc
     override func supportedEvents() -> [String]! {
        return EventEmitter.sharedInstance.allEvents
    }
    
    @objc
    override static func requiresMainQueueSetup() -> Bool {
        return false
    }
}

enum modeState {
    case advertiser
    case discoverer
}
