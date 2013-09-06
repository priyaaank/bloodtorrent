calatrava.pageView ?= {}

calatrava.pageView.donationRequestListing = ->

  $page = $('#donationRequestListing')
  $p = (sel)-> $(sel, $page)

  renderAllRequests = ($select, donationRequests) ->
    $p("#loadingBanner").hide()
    if (donationRequests? && donationRequests.length > 0) then renderDonations($select, donationRequests) else showNoRecordsBanner($select)

  renderDonations = ($select, donationRequests) ->
    $select.empty().html ich.donationListingTmpl
      donations: donationRequests

  showNoRecordsBanner = ($select) ->
    $select.empty().html ich.noDonationRecordsBannerTmpl()

  renderSection = (key, data) ->
    switch key
      when 'donations' then renderAllRequests($p('#donationListing'), data)
      else p("#" + key).val(data)

  triggerListingRefresh = (location)->
    coordinates =
      latitude: location.coords.latitude
      longitude: location.coords.longitude
    $p('#donationListing').trigger("refreshDonations", JSON.stringify(coordinates))

  bind: (event, handler) ->
    switch event
      when "refreshDonations" then $p('#donationListing').off('refreshDonations').on 'refreshDonations', (event) -> handler(event.data)
      else $p("#" + event).off('click').on 'click', handler

  render: (message) ->
    renderSection(section, data) for own section,data of message

  get: (field) ->
    $page.find("#" + field).val()

  show: ->
    $p("#loadingBanner").show()
    $p('#donationListing').empty()
    $page.show()
    navigator.geolocation.getCurrentPosition(triggerListingRefresh)

  hide: ->
    $page.hide()