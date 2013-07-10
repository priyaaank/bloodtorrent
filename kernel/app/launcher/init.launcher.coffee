bloodtorrent.launcher ?= {}

bloodtorrent.launcher.launch = () ->

  runThroughInitialSetup = () ->
    bloodtorrent.settings.start(startApp)

  startApp = () -> bloodtorrent.menu.start();

  initialize = () ->
    calatrava.preferences.retrieve "firstTimeSetup", (isSetupComplete) ->
      if isSetupComplete then startApp() else runThroughInitialSetup()

  initialize()