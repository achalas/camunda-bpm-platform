/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.camunda.bpm.client.task.impl.dto;

import java.util.Map;

import org.camunda.bpm.client.impl.RequestDto;

/**
 * @author Tassilo Weidner
 */
public class BpmnErrorRequestDto extends RequestDto {

  protected String errorCode;
  protected String errorMessage;
  protected Map<String, Object> variables;

  public BpmnErrorRequestDto(String workerId, String errorCode) {
    super(workerId);
    this.errorCode = errorCode;
  }

  public BpmnErrorRequestDto(String workerId, String errorCode, String errorMessage) {
    this(workerId, errorCode);
    this.errorMessage = errorMessage;
  }

  public BpmnErrorRequestDto(String workerId, String errorCode, String errorMessage, Map<String, Object> variables) {
    this(workerId, errorCode, errorMessage);
    this.variables = variables;
  }

  public String getErrorCode() {
    return errorCode;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public Map<String, Object> getVariables() {
    return variables;
  }

}