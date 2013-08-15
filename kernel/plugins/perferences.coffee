calatrava.preferences ?= {}

calatrava.preferences.add = (key, value) ->
  calatrava.bridge.plugins.call 'preferences', 'add',
    message:
      key   : key
      value : value

calatrava.preferences.retrieve = (key, onOkExecute) ->
  okCallbackHandle = calatrava.bridge.plugins.rememberCallback (value) ->
    calatrava.bridge.plugins.deleteCallback(okCallbackHandle)
    onOkExecute(value)

  calatrava.bridge.plugins.call 'preferences', 'retrieve',
    message:
      key: key
    okHandler: okCallbackHandle