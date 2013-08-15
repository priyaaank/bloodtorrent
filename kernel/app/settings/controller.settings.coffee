bloodtorrent ?= {}
bloodtorrent.settings ?= {}

bloodtorrent.settings.controller = ({views, changePage, settingsSaved}) ->

  initialize = () ->
    renderExistingValues()
    captureUserSettings()

  persistPreferenceValues = (options) ->
    calatrava.preferences.add("bloodGroup", options.bloodGroup)
    calatrava.preferences.add("notificationRadius", options.notificationRadius)
    calatrava.preferences.add("userName", options.userName)

  savePreferences = () ->
    valueHash = {}
    fields = ["bloodGroup", "notificationRadius", "userName"]

    saveSettingsAndContinue = _.after fields.length, () ->
      persistPreferenceValues(valueHash)
      calatrava.preferences.add("firstTimeSetup", "Done")
      settingsSaved()

    _.each fields, (fieldName) ->
      views.userSetupPage.get fieldName, (value) ->
        valueHash[fieldName] = value
        saveSettingsAndContinue()

  captureUserSettings = () ->
    views.userSetupPage.bind "saveUserPreferences", savePreferences

  renderExistingValues = () ->
    valueHash = {}
    fields = ["bloodGroup", "notificationRadius", "userName"]

    renderOnceAllFieldsAreFetched = _.after fields.length, () ->
      changePage("userSetup")
      views.userSetupPage.render
        initValues:
          valueHash

    _.each fields, (fieldName) ->
      calatrava.preferences.retrieve fieldName, (value) ->
        valueHash[fieldName] = value
        renderOnceAllFieldsAreFetched()

  initialize()
