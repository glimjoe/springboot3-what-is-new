package com.tongweb.springboot3.micrometer;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationHandler;

public class SimpleLoggingHandler  implements ObservationHandler<Observation.Context> {
  @Override
  public void onStart(Observation.Context context) {
    ObservationHandler.super.onStart(context);
  }

  @Override
  public void onError(Observation.Context context) {
    ObservationHandler.super.onError(context);
  }

  @Override
  public void onEvent(Observation.Event event, Observation.Context context) {
    ObservationHandler.super.onEvent(event, context);
  }

  @Override
  public void onScopeOpened(Observation.Context context) {
    ObservationHandler.super.onScopeOpened(context);
  }

  @Override
  public void onScopeClosed(Observation.Context context) {
    ObservationHandler.super.onScopeClosed(context);
  }

  @Override
  public void onScopeReset(Observation.Context context) {
    ObservationHandler.super.onScopeReset(context);
  }

  @Override
  public void onStop(Observation.Context context) {
    ObservationHandler.super.onStop(context);
  }

  @Override
  public boolean supportsContext(Observation.Context context) {
    return true;
  }
}
