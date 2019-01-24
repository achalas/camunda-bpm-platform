/*
 * Copyright © 2013-2018 camunda services GmbH and various authors (info@camunda.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.camunda.bpm.engine.test.standalone.pvm.activities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.camunda.bpm.engine.impl.pvm.PvmActivity;
import org.camunda.bpm.engine.impl.pvm.PvmTransition;
import org.camunda.bpm.engine.impl.pvm.delegate.ActivityExecution;
import org.camunda.bpm.engine.impl.pvm.delegate.CompositeActivityBehavior;


/**
 * @author Tom Baeyens
 */
public class EmbeddedSubProcess implements CompositeActivityBehavior {

  public void execute(ActivityExecution execution) throws Exception {
    List<PvmActivity> startActivities = new ArrayList<PvmActivity>();
    for (PvmActivity activity: execution.getActivity().getActivities()) {
      if (activity.getIncomingTransitions().isEmpty()) {
        startActivities.add(activity);
      }
    }

    for (PvmActivity startActivity: startActivities) {
      execution.executeActivity(startActivity);
    }
  }

  public void concurrentChildExecutionEnded(ActivityExecution scopeExecution, ActivityExecution endedExecution) {
    endedExecution.remove();
    scopeExecution.tryPruneLastConcurrentChild();
  }

  public void complete(ActivityExecution execution) {
    List<PvmTransition> outgoingTransitions = execution.getActivity().getOutgoingTransitions();
    if(outgoingTransitions.isEmpty()) {
      execution.end(true);
    }else {
      execution.leaveActivityViaTransitions(outgoingTransitions, Collections.EMPTY_LIST);
    }
  }

}