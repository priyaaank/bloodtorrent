bloodtorrent.launcher ?= {}

bloodtorrent.launcher.launch = () ->

  runThroughInitialSetup = () ->
    bloodtorrent.settings.start(true, startApp)

  startApp = () -> bloodtorrent.menu.start();

  initialize = () ->
    calatrava.preferences.retrieve "firstTimeSetup", (isSetupComplete) ->
      if isSetupComplete == "Done" then startApp() else runThroughInitialSetup()

  initialize()