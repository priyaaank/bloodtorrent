bloodtorrent ?= {}
bloodtorrent.models ?= {}

bloodtorrent.models.donationRequest = class DonationRequest

  BLOOD_GROUPS =
    "A+": "apositive"
    "A-": "anegative"
    "O+": "opositive"
    "O-": "onegative"
    "B+": "bpositive"
    "B-": "bnegative"
    "AB+": "abpositive"
    "AB-": "abnegative"

  integerRegex = /^\d+$/

  constructor: ({bloodGroup, units, location, contactDetails, requestor}) ->
    @bloodGroup = BLOOD_GROUPS[bloodGroup.toUpperCase()] || "unknown"
    @units = units
    @location = location
    @contactDetails = contactDetails
    @requestor = requestor

  isInteger = (value) ->
    integerRegex.test value

  isValid : () ->
    @errors = []
    @errors.push("Invalid blood group") if (@bloodGroup is "unknown")
    @errors.push("Units is not an integer") unless isInteger(@units)
    @errors.push("Contact details must be supplied") if _(@contactDetails).isEmpty()
    _.isEmpty(@errors)