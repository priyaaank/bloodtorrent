calatrava.pageView ?= {}

calatrava.pageView.captureLocation = ->

  $page = $('#captureLocation')
  $p = (sel)-> $(sel, $page)
  mapHandle = null
  selectedLocationMarker = null
  selectedLocation = null

  renderMap = () ->
    if mapHandle == null then initializeMap() else updateUserMarkerLocation(selectedLocation)

  initializeMap = () ->
    mapContainer = document.getElementById("mapContainer")
    currentUserLocation
    options =
      mapTypeId: google.maps.MapTypeId.ROADMAP
      center: new google.maps.LatLng(0, 0)
      zoom: 12
    mapHandle = new google.maps.Map(mapContainer,options)
    google.maps.event.addListener mapHandle, 'click', (event) ->
      updateUserMarkerLocation(event.latLng);
    currentUserLocation(extarctCordinatesAndUpdateLocation)

  extarctCordinatesAndUpdateLocation = (location) ->
    latitude = location.coords.latitude
    longitude = location.coords.longitude
    coordinates = new google.maps.LatLng(latitude, longitude)
    updateLocation(coordinates)

  currentUserLocation = (callback) ->
    navigator.geolocation.getCurrentPosition(callback)

  updateLocation = (geoPosition) ->
    selectedLocationMarker = new google.maps.Marker
      position: mapHandle.getCenter()
      map: mapHandle
      title: "Blood donation here"
    updateUserMarkerLocation(geoPosition)

  updateUserMarkerLocation = (geoLocation) ->
    mapHandle.panTo(geoLocation)
    selectedLocationMarker.setPosition(geoLocation);
    selectedLocation = geoLocation

  renderSection = (key, data) ->
    switch key
      when 'mapView' then renderMap()
      else p("#" + key).val(data)

  bind: (event, handler) ->
    switch event
      when "selectLocation" then $p("#selectLocation").off('click').on 'click', () -> handler(selectedLocation)
      else $p("#" + event).off('click').on 'click', handler

  render: (message) ->
    renderSection(section, data) for own section,data of message

  get: (field) ->
    $page.find("#" + field).val()

  show: ->
    $page.show()

  hide: ->
    $page.hide()