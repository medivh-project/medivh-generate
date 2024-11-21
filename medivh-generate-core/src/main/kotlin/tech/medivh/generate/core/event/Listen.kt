package tech.medivh.generate.core.event


/**
 * dynamic listen to event
 * method that add this annotation will be called when event is published.
 * ensure the method has only one parameter and the parameter is the event type.
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class Listen
