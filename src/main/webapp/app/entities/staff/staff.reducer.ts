import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IStaff, defaultValue } from 'app/shared/model/staff.model';

export const ACTION_TYPES = {
  FETCH_STAFF_LIST: 'staff/FETCH_STAFF_LIST',
  FETCH_STAFF: 'staff/FETCH_STAFF',
  CREATE_STAFF: 'staff/CREATE_STAFF',
  UPDATE_STAFF: 'staff/UPDATE_STAFF',
  DELETE_STAFF: 'staff/DELETE_STAFF',
  RESET: 'staff/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IStaff>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type StaffState = Readonly<typeof initialState>;

// Reducer

export default (state: StaffState = initialState, action): StaffState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_STAFF_LIST):
    case REQUEST(ACTION_TYPES.FETCH_STAFF):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_STAFF):
    case REQUEST(ACTION_TYPES.UPDATE_STAFF):
    case REQUEST(ACTION_TYPES.DELETE_STAFF):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_STAFF_LIST):
    case FAILURE(ACTION_TYPES.FETCH_STAFF):
    case FAILURE(ACTION_TYPES.CREATE_STAFF):
    case FAILURE(ACTION_TYPES.UPDATE_STAFF):
    case FAILURE(ACTION_TYPES.DELETE_STAFF):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_STAFF_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_STAFF):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_STAFF):
    case SUCCESS(ACTION_TYPES.UPDATE_STAFF):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_STAFF):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {},
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState,
      };
    default:
      return state;
  }
};

const apiUrl = 'api/staff';

// Actions

export const getEntities: ICrudGetAllAction<IStaff> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_STAFF_LIST,
  payload: axios.get<IStaff>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<IStaff> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_STAFF,
    payload: axios.get<IStaff>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IStaff> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_STAFF,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IStaff> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_STAFF,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IStaff> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_STAFF,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
