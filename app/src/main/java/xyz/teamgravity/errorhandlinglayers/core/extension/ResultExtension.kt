package xyz.teamgravity.errorhandlinglayers.core.extension

import xyz.teamgravity.errorhandlinglayers.core.util.UniversalText
import xyz.teamgravity.errorhandlinglayers.domain.error.DataError
import xyz.teamgravity.errorhandlinglayers.domain.util.Result

fun Result.Error<*, DataError>.asErrorText(): UniversalText {
    return error.asText()
}