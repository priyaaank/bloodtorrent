bloodtorrent ?= {}
bloodtorrent.donationRequest ?= {}

bloodtorrent.donationRequest.repository = ({ajax}) ->

  requestDonations : (options) ->
    url = "#{bloodtorrent.environment().apiEndpoint}/donation/search"
    queryString = "?blood_group=#{options.bloodGroup}&latitude=#{options.location.latitude}&longitude=#{options.location.longitude}&radius=#{options.radius}"
    ajax
      url: url + queryString
      method: "GET"
      contentType: "application/json"
      success: (responseData) ->
        options.successCallback JSON.parse(responseData)
      failure: (errorCode, errorMsg) ->
        options.failureCallback {"status" : "Error", errorMessage : "Shucks! The call to server failed. Is you internet connection active?" }

  createDonation : (options) ->
    url = "#{bloodtorrent.environment().apiEndpoint}/donation/new"
    ajax
      url: url
      method: "POST"
      contentType: "application/json"
      body:
        blood_group     : options.donationRequest.bloodGroup
        latitude        : options.donationRequest.location.latitude
        longitude       : options.donationRequest.location.longitude
        quantity        : options.donationRequest.units
        requestor       : options.donationRequest.requestor || "unknown"
        contact_details : options.donationRequest.contactDetails
      success: (responseData) ->
        parsedResponse = if _(responseData).isEmpty() then "success" else JSON.parse(responseData)
        options.onDonationCreateSuccess parsedResponse
      failure: (errorCode, errorMsg) ->
        options.onDonationCreateFailure {"status" : "Error", errorMessage : errorMsg }
