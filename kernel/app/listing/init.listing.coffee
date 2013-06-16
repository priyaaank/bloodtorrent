bloodtorrent ?= {}
bloodtorrent.listing ?= {}

bloodtorrent.listing.start = ->
  bloodtorrent.listing.controller
    views:
      listingPage: calatrava.bridge.pages.pageNamed "listingPage"
    repositories:
      donationListing: bloodtorrent.listing.repository(ajax: calatrava.bridge.request)
    changePage: calatrava.bridge.changePage
    ajax: calatrava.bridge.request

  calatrava.bridge.changePage "listingPage"
