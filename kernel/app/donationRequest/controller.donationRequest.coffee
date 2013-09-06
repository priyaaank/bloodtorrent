bloodtorrent ?= {}
bloodtorrent.donationRequest ?= {}

bloodtorrent.donationRequest.controller = ({views, repositories, changePage}) ->

  lookupBloodGroup = null
  lookupRadius = null
  selectedLocation =
    latitude: null
    longitude: null

  goHome = () ->
    changePage("menu")

  showDonationListing = () ->
    views.donationRequestListingPage.bind "refreshDonations", (location) ->
      fetchAndUpdateDonationListing(location)
    requestDonations()

  showNewDonationPage = () ->
    changePage("newDonationRequest")
    bindCreateDonationView()

  showLocationCapturePage = () ->
    changePage("captureLocation")
    views.captureLocationPage.render
      mapView: ""
    bindCaptureLocationPage()

  updateLocationForDonation = (latitude, longitude) ->
    selectedLocation.latitude = latitude
    selectedLocation.longitude = longitude

  successCallback = (successResponse) ->
    views.donationRequestListingPage.render
      donations: successResponse

  failureCallback = (error) ->
    calatrava.alert(error.errorMessage)

  fetchAndUpdateDonationListing = (cordinates) ->
    location = JSON.parse(cordinates)
    options =
      successCallback: successCallback
      failureCallback: failureCallback
      bloodGroup: lookupBloodGroup
      location:
        latitude: location.latitude
        longitude: location.longitude
      radius: lookupRadius

    repositories.donationsRepository.requestDonations(options)

  requestDonations = () ->
    calatrava.preferences.retrieve "notificationRadius", (radius) -> lookupRadius = radius
    calatrava.preferences.retrieve "bloodGroup", (bloodgroup) ->
      lookupBloodGroup = bloodgroup
      changePage("donationRequestListing")

  bindCaptureLocationPage = () ->
    views.captureLocationPage.bind "updateLocationForDonation", updateLocationForDonation
    views.captureLocationPage.bind "selectLocation", (location) ->
      updateLocationForDonation(location.lat(), location.lng())
      changePage "newDonationRequest"

  bindCreateDonationView = () ->
    views.newDonationRequestPage.bind "submitDonationRequest", validateAndCreateDonationRequest
    views.newDonationRequestPage.bind "showLocationCapturePage", showLocationCapturePage
    views.newDonationRequestPage.bind "goHome", goHome

  createNewRequest = (donationRequest) ->
    repositories.donationsRepository.createDonation({donationRequest, onDonationCreateSuccess, onDonationCreateFailure})

  onDonationCreateSuccess = (successResponse) ->
    views.newDonationRequestPage.render
      back: ""

  onDonationCreateFailure = (errorCode, errorMessage) ->
    calatrava.alert("Oops! That did not go through. Would you please try it later?")

  renderErrors = (errors) ->
    calatrava.alert("Oh No! Either you missed map location or contact details or both")

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
      location = selectedLocation
      donationRequest = new bloodtorrent.models.donationRequest({bloodGroup, units, location, contactDetails, requestor})
      errors = donationRequest.errors unless donationRequest.isValid()
      if _.isEmpty(errors) then createNewRequest(donationRequest) else renderErrors()

  initialize = () ->
    showDonationListing: showDonationListing
    showNewDonationPage: showNewDonationPage

  initialize()