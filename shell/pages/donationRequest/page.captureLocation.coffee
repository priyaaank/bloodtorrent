calatrava.pageView ?= {}

calatrava.pageView.captureLocation = ->

  $page = $('#captureLocation')
  $p = (sel)-> $(sel, $page)
  mapHandle = null

  renderMap = () ->
    mapContainer = document.getElementById("mapContainer")
    currentUserLocation
    options =
      mapTypeId: google.maps.MapTypeId.ROADMAP,
      center: new google.maps.LatLng(0, 0),
      zoom: 12
    mapHandle = new google.maps.Map(mapContainer,options)
    currentUserLocation(updateLocation)

  currentUserLocation = (callback) ->
    navigator.geolocation.getCurrentPosition(callback)

  updateLocation = (geoPosition) ->
    latitude = geoPosition.coords.latitude
    longitude = geoPosition.coords.longitude
    location = new google.maps.LatLng(latitude, longitude)
    mapHandle.panTo(location)
#  var updateLocation = function(geoPosition) {
#  var latitude = geoPosition.coords.latitude;
#  var longitude = geoPosition.coords.longitude;
#  var location = new google.maps.LatLng(latitude,longitude)
#  moveNewLocationToCenter(location);
#  addMarkerAtCenter();
#  updateUserMarkerLocation(location);
#  };

  renderSection = (key, data) ->
    switch key
      when 'mapView' then renderMap($p('#mapContainer'))
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