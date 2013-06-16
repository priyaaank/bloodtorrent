exports = require 'spec_helper'

example = exports.bloodtorrent
stubView = exports.stubView

describe 'listing controller', ->
  ajax = null
  changePage = null
  views = null

  beforeEach ->
    ajax = jasmine.createSpy("ajax requester")
    changePage = jasmine.createSpy('page changer').andCallFake (targetPage) ->
      if views[targetPage].boundEvents['pageOpened']?
        views[targetPage].trigger 'pageOpened'
    views =
      listingPage: stubView.create('listingPage')

    subject = bloodtorrent.listing.controller
      changePage: changePage
      views: views
      ajax: ajax

