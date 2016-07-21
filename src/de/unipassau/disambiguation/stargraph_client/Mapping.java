package de.unipassau.disambiguation.stargraph_client;

import java.util.ArrayList;
import java.util.List;

import de.unipassau.disambiguation.ResolvedEntity;

public class Mapping {

	private String entityTerm;	
	private String variable;
	private EntityType entityType;
	private List<ResolvedEntity> resolvedEntities = new ArrayList<>();
	
	public Mapping(String entityTerm, EntityType entityType, List<ResolvedEntity> resolvedEntities) {
		this.entityTerm = entityTerm;
		this.entityType = entityType;
		this.resolvedEntities = resolvedEntities;
	}
	
	public Mapping(String entityTerm, EntityType entityType, String variable, List<ResolvedEntity> resolvedEntities) {
		this.entityTerm = entityTerm;
		this.entityType = entityType;
		this.variable = variable;
		this.resolvedEntities = resolvedEntities;
	}

	public String getEntityTerm() {
		return entityTerm;
	}

	public void setEntityTerm(String entityTerm) {
		this.entityTerm = entityTerm;
	}

	public List<ResolvedEntity> getMappedEntities() {
		return resolvedEntities;
	}

	public void setMappedEntities(List<ResolvedEntity> resolvedEntities) {
		this.resolvedEntities = resolvedEntities;
	}
	
	public EntityType getEntityType() {
		return entityType;
	}

	public void setEntityType(EntityType entityType) {
		this.entityType = entityType;
	}

	public String getVariable() {
		return variable;
	}

	public void setVariable(String variable) {
		this.variable = variable;
	}

	public List<ResolvedEntity> getResolvedEntities() {
		return resolvedEntities;
	}

	public void setResolvedEntities(List<ResolvedEntity> resolvedEntities) {
		this.resolvedEntities = resolvedEntities;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((entityTerm == null) ? 0 : entityTerm.hashCode());
		result = prime * result
				+ ((entityType == null) ? 0 : entityType.hashCode());
		result = prime
				* result
				+ ((resolvedEntities == null) ? 0 : resolvedEntities.hashCode());
		result = prime * result
				+ ((variable == null) ? 0 : variable.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mapping other = (Mapping) obj;
		if (entityTerm == null) {
			if (other.entityTerm != null)
				return false;
		} else if (!entityTerm.equals(other.entityTerm))
			return false;
		if (entityType != other.entityType)
			return false;
		if (resolvedEntities == null) {
			if (other.resolvedEntities != null)
				return false;
		} else if (!resolvedEntities.equals(other.resolvedEntities))
			return false;
		if (variable == null) {
			if (other.variable != null)
				return false;
		} else if (!variable.equals(other.variable))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Mapping [entityTerm=" + entityTerm + ", variable=" + variable
				+ ", entityType=" + entityType + ", resolvedEntities="
				+ resolvedEntities + "]";
	}
	
}
