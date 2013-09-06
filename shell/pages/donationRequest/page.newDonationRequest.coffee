calatrava.pageView ?= {}

calatrava.pageView.newDonationRequest = ->

  $page = $('#newDonationRequest')
  $p = (sel)-> $(sel, $page)

  renderSection = (key, data) ->
    switch key
      when "back" then $p("#goHome").triggerHandler("goHome")
      else $p("#" + key).val(data)

  bind: (event, handler) ->
    switch event
      when "showLocationCapturePage" then $p("#location").off("click").on 'click', handler
      when "goHome" then $p("#goHome").off("goHome").on 'goHome', handler
      else $p("#" + event).off('click').on 'click', handler

  render: (message) ->
    renderSection(section, data) for own section,data of message

  get: (field) ->
    $page.find("#" + field).val()

  show: ->
    $page.show()

  hide: ->
    $page.hide()