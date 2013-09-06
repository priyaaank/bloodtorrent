calatrava.pageView ?= {}

calatrava.pageView.userSetup = ->

  $page = $('#userSetup')
  $p = (sel)-> $(sel, $page)
  $radiusDropdownIsInitialized = false

  initializeRadiusDropdown = () ->
    radiusDropdown = $p('#notificationRadius')
    radiusDropdown.append(new Option(radiusValue + " kms", radiusValue)) for radiusValue in [5,10,15,25,50,75,100,150,200,300]
    $radiusDropdownIsInitialized = true

  initializeValues = ($select, data) ->
    initializeRadiusDropdown() unless $radiusDropdownIsInitialized
    $select.find("#userName").val(data.userName)
    $select.find("#notificationRadius").val(data.notificationRadius)
    $select.find("#bloodGroup").val(data.bloodGroup)

  renderSection = (key, data) ->
    switch key
      when 'initValues' then initializeValues($p('#setupData'), data)
      when 'transitionNext' then $p('#saveUserPreferences').triggerHandler("transition:next")
      else $p("#" + key).val(data)

  bind: (event, handler) ->
    switch event
      when "transitionNext" then $p("#saveUserPreferences").off("transition:next").on 'transition:next', handler
      else $p("#" + event).off('click').on 'click', handler

  render: (message) ->
    renderSection(section, data) for own section,data of message

  get: (field) ->
    $page.find("#" + field).val()

  show: ->
    $page.show()

  hide: ->
    $page.hide()