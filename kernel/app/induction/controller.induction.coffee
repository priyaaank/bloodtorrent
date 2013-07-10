bloodtorrent ?= {}
bloodtorrent.induction ?= {}

bloodtorrent.induction.controller = ({views, changePage}) ->

  initialize = () ->
    calatrava.preferences.retrieve "firstTimeSetup", (isSetupComplete) ->
      if isSetupComplete then startApp() else runThroughInitialSetup()

  persistPreferenceValues = (options) ->
    calatrava.preferences.add("bloodGroup", options.bloodGroup)
    calatrava.preferences.add("notificationRadius", options.radius)
    calatrava.preferences.add("userName", options.userName)

  savePreferences = () ->
    userName = null
    bloodGroup = null
    radius = null
    views.userSetupPage.get "userName", (value) -> userName = value
    views.userSetupPage.get "userBloodGroup", (value) -> bloodGroup = value
    views.userSetupPage.get "notificationRadius", (value) -> radius = value

    persistPreferenceValues({userName, bloodGroup, radius})
    calatrava.preferences.add("firstTimeSetup", "Done")

    startApp()

  runThroughInitialSetup = () ->
    changePage("userSetup")
    views.userSetupPage.bind "saveUserPreferences", savePreferences

  startApp = () -> bloodtorrent.menu.start();

  initialize()
