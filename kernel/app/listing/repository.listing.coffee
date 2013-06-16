bloodtorrent ?= {}
bloodtorrent.listing ?= {}

bloodtorrent.listing.repository = ({ajax}) ->

  requestDonations : (options) ->
    url = "#{calatrava.bridge.environment().serviceEndpoint}/donations"
    ajax
      url: url
      method: "GET"
      body: {blood_group:options.blood_group, latitude:options.location.latitude, longitude: options.location.longitude, radius: options.radius}
      contentType: "application/json"
      success: (responseData) =>
        options.loginSuccessCallback JSON.parse(responseData)
      failure: (errorCode, errorMsg) =>
        options.loginFailureCallback {"status" : "Error", errorMessage : errorMsg }
