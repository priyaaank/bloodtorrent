bloodtorrent ?= {}
bloodtorrent.settings ?= {}

bloodtorrent.settings.start = ->
  bloodtorrent.settings.controller
    views:
      userSetupPage: calatrava.bridge.pages.pageNamed "userSetup"
    changePage: calatrava.bridge.changePage