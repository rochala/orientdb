/* Generated By:JJTree: Do not edit this line. OCreateEdgeStatement.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=true,NODE_PREFIX=O,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package com.orientechnologies.orient.core.sql.parser;

import com.orientechnologies.orient.core.command.OBasicCommandContext;
import com.orientechnologies.orient.core.command.OCommandContext;
import com.orientechnologies.orient.core.db.ODatabase;
import com.orientechnologies.orient.core.sql.executor.OCreateEdgeExecutionPlanner;
import com.orientechnologies.orient.core.sql.executor.OInsertExecutionPlan;
import com.orientechnologies.orient.core.sql.executor.OResultSet;

import java.util.HashMap;
import java.util.Map;

public class OCreateEdgeStatement extends OStatement {

  protected OIdentifier targetClass;
  protected OIdentifier targetClusterName;

  protected boolean upsert = false;

  protected OExpression leftExpression;

  protected OExpression rightExpression;

  protected OInsertBody body;
  protected Number      retry;
  protected Number      wait;
  protected OBatch      batch;

  public OCreateEdgeStatement(int id) {
    super(id);
  }

  public OCreateEdgeStatement(OrientSql p, int id) {
    super(p, id);
  }

  @Override
  public OResultSet execute(ODatabase db, Object[] args, OCommandContext parentCtx) {
    OBasicCommandContext ctx = new OBasicCommandContext();
    if (parentCtx != null) {
      ctx.setParentWithoutOverridingChild(parentCtx);
    }
    ctx.setDatabase(db);
    Map<Object, Object> params = new HashMap<>();
    if (args != null) {
      for (int i = 0; i < args.length; i++) {
        params.put(i, args[i]);
      }
    }
    ctx.setInputParameters(params);
    OInsertExecutionPlan executionPlan = createExecutionPlan(ctx, false);
    executionPlan.executeInternal();
    return new OLocalResultSet(executionPlan);
  }

  @Override
  public OResultSet execute(ODatabase db, Map params, OCommandContext parentCtx) {
    OBasicCommandContext ctx = new OBasicCommandContext();
    if (parentCtx != null) {
      ctx.setParentWithoutOverridingChild(parentCtx);
    }
    ctx.setDatabase(db);
    ctx.setInputParameters(params);
    OInsertExecutionPlan executionPlan = createExecutionPlan(ctx, false);
    executionPlan.executeInternal();
    return new OLocalResultSet(executionPlan);
  }

  public OInsertExecutionPlan createExecutionPlan(OCommandContext ctx, boolean enableProfiling) {
    OCreateEdgeExecutionPlanner planner = new OCreateEdgeExecutionPlanner(this);
    OInsertExecutionPlan result = planner.createExecutionPlan(ctx, enableProfiling);
    result.setStatement(this.originalStatement);
    return result;
  }

  public void toString(Map<Object, Object> params, StringBuilder builder) {
    builder.append("CREATE EDGE");
    if (targetClass != null) {
      builder.append(" ");
      targetClass.toString(params, builder);
      if (targetClusterName != null) {
        builder.append(" CLUSTER ");
        targetClusterName.toString(params, builder);
      }
    }
    if (upsert) {
      builder.append(" UPSERT");
    }
    builder.append(" FROM ");
    leftExpression.toString(params, builder);

    builder.append(" TO ");
    rightExpression.toString(params, builder);

    if (body != null) {
      builder.append(" ");
      body.toString(params, builder);
    }
    if (retry != null) {
      builder.append(" RETRY ");
      builder.append(retry);
    }
    if (wait != null) {
      builder.append(" WAIT ");
      builder.append(wait);
    }
    if (batch != null) {
      batch.toString(params, builder);
    }
  }

  @Override
  public boolean executinPlanCanBeCached() {
    if (this.leftExpression != null && !this.leftExpression.isCacheable()) {
      return false;
    }
    if (this.rightExpression != null && !this.rightExpression.isCacheable()) {
      return false;
    }
    if (this.body != null && !body.isCacheable()) {
      return false;
    }
    return true;
  }

  @Override
  public OCreateEdgeStatement copy() {
    OCreateEdgeStatement result = null;
    try {
      result = getClass().getConstructor(Integer.TYPE).newInstance(-1);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    result.targetClass = targetClass == null ? null : targetClass.copy();
    result.targetClusterName = targetClusterName == null ? null : targetClusterName.copy();

    result.upsert = this.upsert;

    result.leftExpression = leftExpression == null ? null : leftExpression.copy();

    result.rightExpression = rightExpression == null ? null : rightExpression.copy();

    result.body = body == null ? null : body.copy();
    result.retry = retry;
    result.wait = wait;
    result.batch = batch == null ? null : batch.copy();
    return result;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;

    OCreateEdgeStatement that = (OCreateEdgeStatement) o;

    if (upsert != that.upsert)
      return false;
    if (targetClass != null ? !targetClass.equals(that.targetClass) : that.targetClass != null)
      return false;
    if (targetClusterName != null ? !targetClusterName.equals(that.targetClusterName) : that.targetClusterName != null)
      return false;
    if (leftExpression != null ? !leftExpression.equals(that.leftExpression) : that.leftExpression != null)
      return false;
    if (rightExpression != null ? !rightExpression.equals(that.rightExpression) : that.rightExpression != null)
      return false;
    if (body != null ? !body.equals(that.body) : that.body != null)
      return false;
    if (retry != null ? !retry.equals(that.retry) : that.retry != null)
      return false;
    if (wait != null ? !wait.equals(that.wait) : that.wait != null)
      return false;
    return batch != null ? batch.equals(that.batch) : that.batch == null;
  }

  @Override
  public int hashCode() {
    int result = targetClass != null ? targetClass.hashCode() : 0;
    result = 31 * result + (targetClusterName != null ? targetClusterName.hashCode() : 0);
    result = 31 * result + (upsert ? 1 : 0);
    result = 31 * result + (leftExpression != null ? leftExpression.hashCode() : 0);
    result = 31 * result + (rightExpression != null ? rightExpression.hashCode() : 0);
    result = 31 * result + (body != null ? body.hashCode() : 0);
    result = 31 * result + (retry != null ? retry.hashCode() : 0);
    result = 31 * result + (wait != null ? wait.hashCode() : 0);
    result = 31 * result + (batch != null ? batch.hashCode() : 0);
    return result;
  }

  public OIdentifier getTargetClass() {
    return targetClass;
  }

  public void setTargetClass(OIdentifier targetClass) {
    this.targetClass = targetClass;
  }

  public OIdentifier getTargetClusterName() {
    return targetClusterName;
  }

  public void setTargetClusterName(OIdentifier targetClusterName) {
    this.targetClusterName = targetClusterName;
  }

  public OExpression getLeftExpression() {
    return leftExpression;
  }

  public void setLeftExpression(OExpression leftExpression) {
    this.leftExpression = leftExpression;
  }

  public OExpression getRightExpression() {
    return rightExpression;
  }

  public void setRightExpression(OExpression rightExpression) {
    this.rightExpression = rightExpression;
  }

  public OInsertBody getBody() {
    return body;
  }

  public void setBody(OInsertBody body) {
    this.body = body;
  }

  public Number getRetry() {
    return retry;
  }

  public void setRetry(Number retry) {
    this.retry = retry;
  }

  public Number getWait() {
    return wait;
  }

  public void setWait(Number wait) {
    this.wait = wait;
  }

  public OBatch getBatch() {
    return batch;
  }

  public void setBatch(OBatch batch) {
    this.batch = batch;
  }

  public boolean isUpsert() {
    return upsert;
  }
}
/* JavaCC - OriginalChecksum=2d3dc5693940ffa520146f8f7f505128 (do not edit this line) */
