exports = require 'spec_helper'

example = exports.bloodtorrent
stubView = exports.stubView

describe 'listing controller', ->

  views = null
  repositories = null

  beforeEach ->
    views =
      listingPage:
        render: jasmine.createSpy('listingPageRender')
    repositories =
      donationsRepository:
        requestDonations: jasmine.createSpy('requestDonations')

    subject = bloodtorrent.listing.controller
      views: views
      repositories: repositories

  describe "when donation listing call is successful", ->

    beforeEach ()->
      @donationRequestResponse = [
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

      repositories.donationsRepository.requestDonations.mostRecentCall.args[0].successCallback(@donationRequestResponse)

    it "should call render for listing page", ->
      expect(views.listingPage.render).toHaveBeenCalledWith(donations: @donationRequestResponse)


