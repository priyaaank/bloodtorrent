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
        _id: "51c046357e736a3b02000001"
        blood_group: "apositive"
        cordinates: [12.12,11.11]
        quantity: 12
        requestor: "Adam"
        contact_details: "adam@twitter"
      ,
        _id: "51c046357e736a3b02000002"
        blood_group: "apositive"
        cordinates: [10.12,-12.11],
        quantity: 10,
        requestor: "Cassy",
        contact_details: "cassy@gmail"
      ]

      repositories.donationsRepository.requestDonations.mostRecentCall.args[0].successCallback(@donationRequestResponse)

    it "should call render for listing page", ->
      expect(views.listingPage.render).toHaveBeenCalledWith(donations: @donationRequestResponse)


