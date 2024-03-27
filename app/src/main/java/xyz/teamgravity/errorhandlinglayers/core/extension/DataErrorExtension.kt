package xyz.teamgravity.errorhandlinglayers.core.extension

import xyz.teamgravity.errorhandlinglayers.R
import xyz.teamgravity.errorhandlinglayers.core.util.UniversalText
import xyz.teamgravity.errorhandlinglayers.domain.error.DataError

fun DataError.asText(): UniversalText {
    return when (this) {
        DataError.Network.RequestTimeout -> UniversalText.Resource(R.string.the_request_timed_out)
        DataError.Network.TooManyRequests -> UniversalText.Resource(R.string.rate_limit)
        DataError.Network.NoInternet -> UniversalText.Resource(R.string.no_internet)
        DataError.Network.PayloadTooLarge -> UniversalText.Resource(R.string.file_too_large)
        DataError.Network.ServerError -> UniversalText.Resource(R.string.server_error)
        DataError.Network.Serialization -> UniversalText.Resource(R.string.error_serialization)
        DataError.Network.Unknown -> UniversalText.Resource(R.string.unknown_error)
        DataError.Local.DiskFull -> UniversalText.Resource(R.string.error_disk_full)
    }
}