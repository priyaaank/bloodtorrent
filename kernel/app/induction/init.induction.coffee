bloodtorrent ?= {}
bloodtorrent.induction ?= {}

bloodtorrent.induction.start = ->
  bloodtorrent.induction.controller
    views:
      userSetupPage: calatrava.bridge.pages.pageNamed "userSetup"
    changePage: calatrava.bridge.changePage