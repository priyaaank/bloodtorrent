calatrava.pageView ?= {}

calatrava.pageView.userSetup = ->

  $page = $('#userSetup')
  $p = (sel)-> $(sel, $page)

  initializeValues = ($select, data) ->
    $select.find("#userName").val(data.userName)
    $select.find("#notificationRadius").val(data.radius)
    $select.find("#userBloodGroup").val(data.bloodGroup)

  renderSection = (key, data) ->
    switch key
      when 'initValues' then initializeValues($p('#setupData'), data)
      else $p("#" + key).val(data)

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