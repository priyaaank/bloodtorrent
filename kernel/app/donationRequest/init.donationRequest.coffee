bloodtorrent ?= {}
bloodtorrent.donationRequest ?= {}

bloodtorrent.donationRequest.start = ->
  bloodtorrent.donationRequest.controller
    views:
      donationRequestListingPage: calatrava.bridge.pages.pageNamed "donationRequestListing"
      newDonationRequestPage: calatrava.bridge.pages.pageNamed "newDonationRequest"
    repositories:
      donationsRepository: bloodtorrent.donationRequest.repository(ajax: calatrava.bridge.request)

  calatrava.bridge.changePage "donationRequestListing"