bloodtorrent ?= {}
bloodtorrent.listing ?= {}

bloodtorrent.listing.controller = ({views, repositories}) ->

  initialize = () ->
    requestDonations()

  successCallback = (successResponse) ->
    views.listingPage.render
      donations: successResponse

  failureCallback = (errorCode, errorResponse) ->
    calatrava.alert(errorResponse)

  requestDonations = () ->
    options =
      successCallback: successCallback
      failureCallback: failureCallback
      bloodGroup: "A+"
      location:
        latitude: 12.12
        longitude: -11.11
      radius: 10

    repositories.donationsRepository.requestDonations(options)

  initialize()
