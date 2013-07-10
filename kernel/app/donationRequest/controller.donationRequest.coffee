bloodtorrent ?= {}
bloodtorrent.donationRequest ?= {}

bloodtorrent.donationRequest.controller = ({views, repositories, changePage}) ->

  showDonationListing = () ->
    changePage("donationRequestListing")
    requestDonations()

  createNewRequest = () ->
    changePage("submitDonationRequest")
    bindCreateDonationView()

  successCallback = (successResponse) ->
    views.donationRequestListingPage.render
      donations: successResponse

  failureCallback = (errorCode, errorResponse) ->
    calatrava.alert(errorResponse)

  requestDonations = () ->
    options =
      successCallback: successCallback
      failureCallback: failureCallback
      bloodGroup: "opositive"
      location:
        latitude: 18.5236
        longitude: 73.8478
      radius: 10000

    repositories.donationsRepository.requestDonations(options)

  bindCreateDonationView = () ->
    views.newDonationRequestPage.bind "submitDonationRequest", validateAndCreateDonationRequest

  createNewRequest = (donationRequest) ->
    repositories.donationsRepository.createDonation({donationRequest, onDonationCreateSuccess, onDonationCreateFailure})

  onDonationCreateSuccess = (successResponse) ->
    calatrava.alert("created new donation")

  onDonationCreateFailure = (errorCode, errorMessage) ->
    calatrava.alert("new donation creation failed")

  renderErrors = (errors) ->
    calatrava.alert("errors")

  validateAndCreateDonationRequest = () ->
    bloodGroup = null
    units = null
    contactDetails = null
    views.newDonationRequestPage.get "bloodgroup", (bloodGroupFromView) -> bloodGroup = bloodGroupFromView
    views.newDonationRequestPage.get "quantity", (unitsFromView) -> units = unitsFromView
    views.newDonationRequestPage.get "contact_details", (contactDetailsFromView) -> contactDetails = contactDetailsFromView
    donationRequest = new bloodtorrent.models.donationRequest({bloodGroup, units, contactDetails})
    errors = donationRequest.errors unless donationRequest.isValid()
    if _.isEmpty(errors) then createNewRequest(donationRequest) else renderErrors()

  initialize = () ->
    showDonationListing: showDonationListing
    createNewRequest: createNewRequest

  initialize()