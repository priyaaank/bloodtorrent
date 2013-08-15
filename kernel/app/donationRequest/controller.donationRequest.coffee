bloodtorrent ?= {}
bloodtorrent.donationRequest ?= {}

bloodtorrent.donationRequest.controller = ({views, repositories, changePage}) ->

  showDonationListing = () ->
    changePage("donationRequestListing")
    requestDonations()

  showNewDonationPage = () ->
    changePage("newDonationRequest")
    bindCreateDonationView()

  successCallback = (successResponse) ->
    views.donationRequestListingPage.render
      donations: successResponse

  failureCallback = (errorCode, errorResponse) ->
    calatrava.alert(errorResponse)

  requestDonations = () ->
    lookupRadius = null
    lookupBloodGroup = null
    calatrava.preferences.retrieve "notificationRadius", (radius) -> lookupRadius = radius
    calatrava.preferences.retrieve "bloodGroup", (bloodgroup) -> lookupBloodGroup = bloodgroup
    options =
      successCallback: successCallback
      failureCallback: failureCallback
      bloodGroup: lookupBloodGroup
      location:
        latitude: 18.5236
        longitude: 73.8478
      radius: lookupRadius

    repositories.donationsRepository.requestDonations(options)

  bindCreateDonationView = () ->
    views.newDonationRequestPage.bind "submitDonationRequest", validateAndCreateDonationRequest

  createNewRequest = (donationRequest) ->
    repositories.donationsRepository.createDonation({donationRequest, onDonationCreateSuccess, onDonationCreateFailure})

  onDonationCreateSuccess = (successResponse) ->
    showDonationListing()

  onDonationCreateFailure = (errorCode, errorMessage) ->
    calatrava.alert("new donation creation failed")

  renderErrors = (errors) ->
    calatrava.alert("errors")

  validateAndCreateDonationRequest = () ->
    bloodGroup = null
    units = null
    contactDetails = null
    requestor = null

    views.newDonationRequestPage.get "bloodGroup", (bloodGroupFromView) -> bloodGroup = bloodGroupFromView
    views.newDonationRequestPage.get "quantity", (unitsFromView) -> units = unitsFromView
    views.newDonationRequestPage.get "contactDetails", (contactDetailsFromView) -> contactDetails = contactDetailsFromView
    calatrava.preferences.retrieve "userName", (requestorNickName) ->
      requestor = requestorNickName
      donationRequest = new bloodtorrent.models.donationRequest({bloodGroup, units, contactDetails, requestor})
      errors = donationRequest.errors unless donationRequest.isValid()
      if _.isEmpty(errors) then createNewRequest(donationRequest) else renderErrors()

  initialize = () ->
    showDonationListing: showDonationListing
    showNewDonationPage: showNewDonationPage

  initialize()