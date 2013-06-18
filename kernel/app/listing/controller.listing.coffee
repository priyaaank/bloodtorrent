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
      bloodGroup: "opositive"
      location:
        latitude: 73.8478
        longitude: 18.5236
      radius: 10

    repositories.donationsRepository.requestDonations(options)

  initialize()
