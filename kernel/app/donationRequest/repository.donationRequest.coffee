bloodtorrent ?= {}
bloodtorrent.donationRequest ?= {}

bloodtorrent.donationRequest.repository = ({ajax}) ->

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

  createDonation : (newDonation) ->
    url = "#{calatrava.bridge.environment().serviceEndpoint}/api/donation/new"
    ajax
      url: url
      method: "POST"
      contentType: "application/json"
      body:
        blood_group     : newDonation.bloodGroup
        latitude        : "12.11"
        longitude       : "11.11"
        quantity        : newDonation.units
        requestor       : "unknown"
        contact_details : newDonation.contactDetails
      success: (responseData) =>
        options.successCallback JSON.parse(responseData)
      failure: (errorCode, errorMsg) =>
        options.failureCallback {"status" : "Error", errorMessage : errorMsg }
