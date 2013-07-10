bloodtorrent ?= {}
bloodtorrent.donationRequest ?= {}

bloodtorrent.donationRequest.init = ->
  bloodtorrent.donationRequest.controller
    views:
      donationRequestListingPage: calatrava.bridge.pages.pageNamed "donationRequestListing"
      newDonationRequestPage: calatrava.bridge.pages.pageNamed "newDonationRequest"
    repositories:
      donationsRepository: bloodtorrent.donationRequest.repository(ajax: calatrava.bridge.request)
    changePage: calatrava.bridge.changePage