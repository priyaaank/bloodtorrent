bloodtorrent ?= {}
bloodtorrent.donationRequest ?= {}

bloodtorrent.donationRequest.start = ->
  bloodtorrent.donationRequest.controller
    views:
      donationRequestListingPage: calatrava.bridge.pages.pageNamed "donationRequestListing"
    repositories:
      donationsRepository: bloodtorrent.donationRequest.repository(ajax: calatrava.bridge.request)
    changePage: calatrava.bridge.changePage

  calatrava.bridge.changePage "donationRequestListing"