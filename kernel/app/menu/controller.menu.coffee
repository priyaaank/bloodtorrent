bloodtorrent ?= {}
bloodtorrent.menu ?= {}

bloodtorrent.menu.controller = ({views, controllers}) ->

  initialize = () ->
    bindMenuOptions()

  bindMenuOptions = () ->
    views.menuPage.bind "donationRequests", showDonationRequestsListing
    views.menuPage.bind "newDonationRequest", showNewDonationRequestPage
    views.menuPage.bind "settings", showSettingsPage

  showDonationRequestsListing = () -> controllers.donationRequest.showDonationListing();
  showNewDonationRequestPage  = () -> controllers.donationRequest.createNewRequest();
  showSettingsPage            = () -> bloodtorrent.settings.start();

  initialize()
