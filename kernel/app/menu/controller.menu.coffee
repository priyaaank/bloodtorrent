bloodtorrent ?= {}
bloodtorrent.menu ?= {}

bloodtorrent.menu.controller = ({views, controllers}) ->

  initialize = () ->
    bindMenuOptions()

  bindMenuOptions = () ->
    views.menuPage.bind "donationRequests", showDonationRequestsListing
    views.menuPage.bind "newDonationRequest", showNewDonationRequestPage
    views.menuPage.bind "settings", showSettingsPage

  showMenu = () ->
    bloodtorrent.menu.start()

  showDonationRequestsListing = () -> controllers.donationRequest.showDonationListing();
  showNewDonationRequestPage  = () -> controllers.donationRequest.showNewDonationPage();
  showSettingsPage            = () -> bloodtorrent.settings.start(false, showMenu);

  initialize()
