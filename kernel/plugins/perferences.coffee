calatrava.preferences ?= {}

calatrava.preferences.add = (key, value, onOkExecute) ->
  okCallbackHandle = calatrava.bridge.plugins.rememberCallback () ->
    calatrava.bridge.plugins.deleteCallback(okCallbackHandle)
    onOkExecute()

  calatrava.bridge.plugins.call 'preferences', 'add',
    message:
      key   : key
      value : value
    okHandler: okCallbackHandle

calatrava.preferences.retrieve = (key, onOkExecute) ->
  okCallbackHandle = calatrava.bridge.plugins.rememberCallback (value) ->
    calatrava.bridge.plugins.deleteCallback(okCallbackHandle)
    onOkExecute(value)

  calatrava.bridge.plugins.call 'preferences', 'retrieve',
    message:
      key: key
    okHandler: okCallbackHandle