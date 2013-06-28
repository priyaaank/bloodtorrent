bloodtorrent ?= {}
bloodtorrent.donationRequest ?= {}

bloodtorrent.donationRequest.controller = ({views, repositories}) ->

  initialize = () ->
    requestDonations()

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
      radius: 50

    repositories.donationsRepository.requestDonations(options)

  initialize()
