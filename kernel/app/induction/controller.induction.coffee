bloodtorrent ?= {}
bloodtorrent.induction ?= {}

bloodtorrent.induction.controller = ({views, changePage}) ->

  initialize = () ->
    calatrava.preferences.retrieve "firstTimeSetup", (isSetupComplete) ->
      if isSetupComplete then startApp() else runThroughInitialSetup()

  runThroughInitialSetup = () ->
    calatrava.preferences.add("firstTimeSetup", "Done")
    startApp()

  startApp = () -> bloodtorrent.donationRequest.start();

  initialize()
