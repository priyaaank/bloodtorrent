exports = require 'spec_helper'
bloodtorrent = exports.bloodtorrent

describe "listing repository", ->

  ajax = null
  onSuccess = null
  onFailure = null
  data = null

  beforeEach ->
    ajax = jasmine.createSpy "ajax requestor"
    onSuccess = jasmine.createSpy "success callback"
    onFailure = jasmine.createSpy "failure callback"
    spyOn(calatrava.bridge, 'environment').andReturn({serviceEndpoint: "endpoint"})

    @subject = bloodtorrent.listing.repository({ajax})

    data =
      radius: 12
      location:
        latitude: 12.12
        longitude: -11.11
      blood_group: "A+"
      successCallback: onSuccess
      failureCallback: onFailure

  describe "request donation request listing", ->

    it "should make call to service end point with correct url and body", ->
      @subject.requestDonations data

      arguments = ajax.mostRecentCall.args[0]

      expect(arguments.url).toEqual("endpoint/donations")
      expect(arguments.method).toEqual("GET")
      expect(arguments.body).toEqual({blood_group:"A+", latitude:12.12, longitude: -11.11, radius: 12})
      expect(arguments.contentType).toEqual("application/json")

    it "should return response data to the success callback when call is successful", ->
      @subject.requestDonations data
      success_reponse = [
          blood_group: "A+"
          cordinates: [12.12,11.11]
          units: 12
          requestor: "Adam"
          contact_details: ["adam@twitter", "9911223344"]
          request_date: ""
      ,
          blood_group: "AB+"
          cordinates: [10.12,-12.11],
          units: 10,
          requestor: "Cassy",
          contact_details: ["cassy@gmail", "9274526262"]
      ]

      arguments = ajax.mostRecentCall.args[0]
      arguments.success JSON.stringify(success_reponse)

      expect(onSuccess).toHaveBeenCalledWith success_reponse


    it "should return error response when call is a failure", ->
      @subject.requestDonations data

      arguments = ajax.mostRecentCall.args[0]
      arguments.failure "error code", "some error"

      expect(onFailure).toHaveBeenCalledWith
        status: "Error"
        errorMessage: "some error"