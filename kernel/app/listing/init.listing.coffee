bloodtorrent ?= {}
bloodtorrent.listing ?= {}

bloodtorrent.listing.start = ->
  bloodtorrent.listing.controller
    views:
      listingPage: calatrava.bridge.pages.pageNamed "listing"
    repositories:
      donationsRepository: bloodtorrent.listing.repository(ajax: calatrava.bridge.request)
    changePage: calatrava.bridge.changePage

  calatrava.bridge.changePage "listing"
