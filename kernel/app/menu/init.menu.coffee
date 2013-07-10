bloodtorrent ?= {}
bloodtorrent.menu ?= {}

bloodtorrent.menu.start = ->
  bloodtorrent.menu.controller
    views:
      menuPage: calatrava.bridge.pages.pageNamed "menu"
    controllers:
      donationRequest: bloodtorrent.donationRequest.init()

  calatrava.bridge.changePage "menu"