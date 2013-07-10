bloodtorrent ?= {}
bloodtorrent.settings ?= {}

bloodtorrent.settings.start = (callback)->
  bloodtorrent.settings.controller
    views:
      userSetupPage: calatrava.bridge.pages.pageNamed "userSetup"
    changePage: calatrava.bridge.changePage
    settingsSaved: callback