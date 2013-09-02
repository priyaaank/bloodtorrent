calatrava.userLocation ?= {}

calatrava.userLocation.cordinates = (onOkExecute) ->
  okCallbackHandle = calatrava.bridge.plugins.rememberCallback (value) ->
    calatrava.bridge.plugins.deleteCallback(okCallbackHandle)
    onOkExecute(value)

  calatrava.bridge.plugins.call 'userLocation', 'cordinates',
    okHandler: okCallbackHandle