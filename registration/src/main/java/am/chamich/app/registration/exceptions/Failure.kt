package am.chamich.app.registration.exceptions


sealed class Failure : Exception() {
    object NetworkConnection : Failure()
    object ServerError : Failure()

    abstract class FeatureFailure : Failure()
}