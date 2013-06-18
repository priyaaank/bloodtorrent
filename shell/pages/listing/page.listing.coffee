calatrava.pageView ?= {}

calatrava.pageView.listing = ->

  $page = $('#listing')
  $p = (sel)-> $(sel, $page)

  renderAllRequests = ($select, donationRequests) ->
    $p("#loadingBanner").hide()
    $select.empty().html ich.donationListingTmpl
      donations: donationRequests

  renderSection = (key, data) ->
    switch key
      when 'donations' then renderAllRequests($p('#donationListing'), data)
      else p("#" + key).val(data)

  bind: (event, handler) ->
    $p("#" + event).off('click').on 'click', handler

  render: (message) ->
    renderSection(section, data) for own section,data of message

  get: (field) ->
    $page.find("#" + field).val()

  show: ->
    $page.show()

  hide: ->
    $page.hide()