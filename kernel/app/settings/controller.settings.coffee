bloodtorrent ?= {}
bloodtorrent.settings ?= {}

bloodtorrent.settings.controller = ({views, changePage, settingsSaved}) ->

  initialize = () ->
    renderExistingValues()
    captureUserSettings()

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

    settingsSaved()

  captureUserSettings = () ->
    changePage("userSetup")
    views.userSetupPage.bind "saveUserPreferences", savePreferences

  renderExistingValues = () ->
    userName = null
    bloodGroup = null
    radius = null

    calatrava.preferences.retrieve "bloodGroup", (value) -> bloodGroup = value
    calatrava.preferences.retrieve "notificationRadius", (value) -> radius = value
    calatrava.preferences.retrieve "userName", (value) -> userName = value

    views.userSetupPage.render
      initValues:
        bloodGroup: bloodGroup
        radius: radius
        userName: userName

  initialize()
