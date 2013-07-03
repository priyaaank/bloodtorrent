calatrava.web ?= {}

calatrava.web.preferences = (method, {message, okHandler}) ->

  if method == 'add'
    if localStorage then localStorage[message.key] = message.value
  else if method == 'retrieve'
    retrievedValue = if localStorage then localStorage[message.key]
    calatrava.bridge.runtime.invokePluginCallback(okHandler, retrievedValue)

calatrava.bridge.runtime.registerPlugin 'preferences', calatrava.web.preferences