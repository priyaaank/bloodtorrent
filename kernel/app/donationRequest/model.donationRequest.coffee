bloodtorrent ?= {}
bloodtorrent.models ?= {}

bloodtorrent.models.donationRequest = class DonationRequest

  bloodGroups =
    "A+": "apositive"
    "A-": "anegative"
    "O+": "opositive"
    "O-": "onegative"
    "B+": "bpositive"
    "B-": "bnegative"
    "AB+": "abpositive"
    "AB-": "abnegative"

  integerRegex = /^\d+$/

  constructor: ({@bloodGroup, @units, @location, @contactDetails}) ->

  isInteger = (value) ->
    integerRegex.test value

  isValid : () ->
    @errors = []
    @errors.push("Invalid blood group") unless (_(bloodGroups).keys().indexOf(@bloodGroup.toUpperCase()) > -1)
    @errors.push("Units is not an integer") unless isInteger(@units)
    @errors.push("Contact details must be supplied") if _(@contactDetails).isEmpty()
    _.isEmpty(@errors)