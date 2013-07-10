calatrava.pageView ?= {}

calatrava.pageView.menu = ->

  $page = $('#menu')
  $p = (sel)-> $(sel, $page)

  renderSection = (key, data) ->
    $p("#" + key).val(data)

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