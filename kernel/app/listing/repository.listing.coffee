bloodtorrent ?= {}
bloodtorrent.listing ?= {}

bloodtorrent.listing.repository = ({ajax}) ->

  requestDonations : (options) ->
    url = "#{calatrava.bridge.environment().serviceEndpoint}/api/donation/search"
    queryString = "?blood_group=#{options.bloodGroup}&latitude=#{options.location.latitude}&longitude=#{options.location.longitude}&radius=#{options.radius}"
    ajax
      url: url + queryString
      method: "GET"
      contentType: "application/json"
      success: (responseData) =>
        options.successCallback JSON.parse(responseData)
      failure: (errorCode, errorMsg) =>
        options.failureCallback {"status" : "Error", errorMessage : errorMsg }
